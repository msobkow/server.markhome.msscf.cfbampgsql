// Description: Java 11 PostgreSQL Jdbc DbIO implementation for ISOTZone.

/*
 *	server.markhome.msscf.CFBam
 *
 *	Copyright (c) 2020-2025 Mark Stephen Sobkow
 *	
 *
 *	Manufactured by MSS Code Factory 2.13
 */

package server.markhome.msscf.cfbam.CFBamPgSql;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.msscf.msscf.cflib.CFLib.*;
import server.markhome.msscf.cfsec.CFSec.*;
import server.markhome.msscf.cfint.CFInt.*;
import server.markhome.msscf.cfbam.CFBam.*;
import server.markhome.msscf.cfsec.CFSecObj.*;
import server.markhome.msscf.cfint.CFIntObj.*;
import server.markhome.msscf.cfbam.CFBamObj.*;

/*
 *	CFBamPgSqlISOTZoneTable PostgreSQL Jdbc DbIO implementation
 *	for ISOTZone.
 */
public class CFBamPgSqlISOTZoneTable
	implements ICFBamISOTZoneTable
{
	private CFBamPgSqlSchema schema;
	protected PreparedStatement stmtReadBuffByPKey = null;
	protected PreparedStatement stmtReadBuffAll = null;
	protected PreparedStatement stmtLockBuffByPKey = null;
	protected PreparedStatement stmtCreateByPKey = null;
	protected PreparedStatement stmtUpdateByPKey = null;
	protected PreparedStatement stmtDeleteByPKey = null;
	protected PreparedStatement stmtReadAllBuff = null;
	protected PreparedStatement stmtReadBuffByIdIdx = null;
	protected PreparedStatement stmtReadBuffByOffsetIdx = null;
	protected PreparedStatement stmtReadBuffByUTZNameIdx = null;
	protected PreparedStatement stmtReadBuffByIso8601Idx = null;
	protected PreparedStatement stmtDeleteByIdIdx = null;
	protected PreparedStatement stmtDeleteByOffsetIdx = null;
	protected PreparedStatement stmtDeleteByUTZNameIdx = null;
	protected PreparedStatement stmtDeleteByIso8601Idx = null;

	public CFBamPgSqlISOTZoneTable( CFBamPgSqlSchema argSchema ) {
		schema = argSchema;
	}

	public void createISOTZone( CFSecAuthorization Authorization,
		CFSecISOTZoneBuff Buff )
	{
		final String S_ProcName = "createISOTZone";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			String Iso8601 = Buff.getRequiredIso8601();
			String TZName = Buff.getRequiredTZName();
			short TZHourOffset = Buff.getRequiredTZHourOffset();
			short TZMinOffset = Buff.getRequiredTZMinOffset();
			String Description = Buff.getRequiredDescription();
			boolean Visible = Buff.getRequiredVisible();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_create_isotz( ?, ?, ?, ?, ?, ?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + " )";
			if( stmtCreateByPKey == null ) {
				stmtCreateByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtCreateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtCreateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtCreateByPKey.setString( argIdx++, "a008" );
			stmtCreateByPKey.setString( argIdx++, Iso8601 );
			stmtCreateByPKey.setString( argIdx++, TZName );
			stmtCreateByPKey.setShort( argIdx++, TZHourOffset );
			stmtCreateByPKey.setShort( argIdx++, TZMinOffset );
			stmtCreateByPKey.setString( argIdx++, Description );
			stmtCreateByPKey.setBoolean( argIdx++, Visible );
			resultSet = stmtCreateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecISOTZoneBuff createdBuff = unpackISOTZoneResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredISOTZoneId( createdBuff.getRequiredISOTZoneId() );
				Buff.setRequiredIso8601( createdBuff.getRequiredIso8601() );
				Buff.setRequiredTZName( createdBuff.getRequiredTZName() );
				Buff.setRequiredTZHourOffset( createdBuff.getRequiredTZHourOffset() );
				Buff.setRequiredTZMinOffset( createdBuff.getRequiredTZMinOffset() );
				Buff.setRequiredDescription( createdBuff.getRequiredDescription() );
				Buff.setRequiredVisible( createdBuff.getRequiredVisible() );
				Buff.setRequiredRevision( createdBuff.getRequiredRevision() );
				Buff.setCreatedByUserId( createdBuff.getCreatedByUserId() );
				Buff.setCreatedAt( createdBuff.getCreatedAt() );
				Buff.setUpdatedByUserId( createdBuff.getUpdatedByUserId() );
				Buff.setUpdatedAt( createdBuff.getUpdatedAt() );
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected a single-record response, " + resultSet.getRow() + " rows selected" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public String S_sqlSelectISOTZoneDistinctClassCode = null;

	public String getSqlSelectISOTZoneDistinctClassCode() {
		if( S_sqlSelectISOTZoneDistinctClassCode == null ) {
			S_sqlSelectISOTZoneDistinctClassCode =
					"SELECT "
					+		"DISTINCT a008.ClassCode "
					+	"FROM " + schema.getLowerDbSchemaName() + ".ISOTz AS a008 ";
		}
		return( S_sqlSelectISOTZoneDistinctClassCode );
	}

	public String S_sqlSelectISOTZoneBuff = null;

	public String getSqlSelectISOTZoneBuff() {
		if( S_sqlSelectISOTZoneBuff == null ) {
			S_sqlSelectISOTZoneBuff =
					"SELECT "
					+		"a008.ISOTZoneId, "
					+		"a008.Iso8601, "
					+		"a008.TZName, "
					+		"a008.TZHourOffset, "
					+		"a008.TZMinOffset, "
					+		"a008.Description, "
					+		"a008.Visible, "
					+		"a008.Revision "
				+	"FROM " + schema.getLowerDbSchemaName() + ".ISOTz AS a008 ";
		}
		return( S_sqlSelectISOTZoneBuff );
	}

	protected CFSecISOTZoneBuff unpackISOTZoneResultSetToBuff( ResultSet resultSet )
	throws SQLException
	{
		final String S_ProcName = "unpackISOTZoneResultSetToBuff";
		int idxcol = 1;
		CFSecISOTZoneBuff buff = schema.getFactoryISOTZone().newBuff();
		{
			String colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setCreatedByUserId( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setCreatedByUserId( null );
			}
			else {
				buff.setCreatedByUserId( UUID.fromString( colString ) );
			}
			idxcol ++;

			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setCreatedAt( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setCreatedAt( null );
			}
			else {
				buff.setCreatedAt( CFBamPgSqlSchema.convertTimestampString( colString ) );
			}
			idxcol++;
			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setUpdatedByUserId( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setUpdatedByUserId( null );
			}
			else {
				buff.setUpdatedByUserId( UUID.fromString( colString ) );
			}
			idxcol ++;

			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setUpdatedAt( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setUpdatedAt( null );
			}
			else {
				buff.setUpdatedAt( CFBamPgSqlSchema.convertTimestampString( colString ) );
			}
			idxcol++;
		}
		buff.setRequiredISOTZoneId( resultSet.getShort( idxcol ) );
		idxcol++;
		buff.setRequiredIso8601( resultSet.getString( idxcol ) );
		idxcol++;
		buff.setRequiredTZName( resultSet.getString( idxcol ) );
		idxcol++;
		buff.setRequiredTZHourOffset( resultSet.getShort( idxcol ) );
		idxcol++;
		buff.setRequiredTZMinOffset( resultSet.getShort( idxcol ) );
		idxcol++;
		buff.setRequiredDescription( resultSet.getString( idxcol ) );
		idxcol++;
		buff.setRequiredVisible( resultSet.getBoolean( idxcol ) );
		idxcol++;

		buff.setRequiredRevision( resultSet.getInt( idxcol ) );
		return( buff );
	}

	public CFSecISOTZoneBuff readDerived( CFSecAuthorization Authorization,
		CFSecISOTZonePKey PKey )
	{
		final String S_ProcName = "readDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecISOTZoneBuff buff;

		buff = readBuff( Authorization, PKey );
		return( buff );
	}

	public CFSecISOTZoneBuff lockDerived( CFSecAuthorization Authorization,
		CFSecISOTZonePKey PKey )
	{
		final String S_ProcName = "lockDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecISOTZoneBuff buff;

		buff = lockBuff( Authorization, PKey );
		return( buff );
	}

	public CFSecISOTZoneBuff[] readAllDerived( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllDerived";
		CFSecISOTZoneBuff[] buffArray;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buffArray = readAllBuff( Authorization );
		return( buffArray );
	}

	public CFSecISOTZoneBuff readDerivedByIdIdx( CFSecAuthorization Authorization,
		short ISOTZoneId )
	{
		final String S_ProcName = "CFBamPgSqlISOTZoneTable.readDerivedByIdIdx() ";
		CFSecISOTZoneBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByIdIdx( Authorization,
				ISOTZoneId );
		return( buff );
	}

	public CFSecISOTZoneBuff[] readDerivedByOffsetIdx( CFSecAuthorization Authorization,
		short TZHourOffset,
		short TZMinOffset )
	{
		final String S_ProcName = "readDerivedByOffsetIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecISOTZoneBuff[] buffList = readBuffByOffsetIdx( Authorization,
				TZHourOffset,
				TZMinOffset );
		return( buffList );

	}

	public CFSecISOTZoneBuff readDerivedByUTZNameIdx( CFSecAuthorization Authorization,
		String TZName )
	{
		final String S_ProcName = "CFBamPgSqlISOTZoneTable.readDerivedByUTZNameIdx() ";
		CFSecISOTZoneBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByUTZNameIdx( Authorization,
				TZName );
		return( buff );
	}

	public CFSecISOTZoneBuff[] readDerivedByIso8601Idx( CFSecAuthorization Authorization,
		String Iso8601 )
	{
		final String S_ProcName = "readDerivedByIso8601Idx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecISOTZoneBuff[] buffList = readBuffByIso8601Idx( Authorization,
				Iso8601 );
		return( buffList );

	}

	public CFSecISOTZoneBuff readBuff( CFSecAuthorization Authorization,
		CFSecISOTZonePKey PKey )
	{
		final String S_ProcName = "readBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			short ISOTZoneId = PKey.getRequiredISOTZoneId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_isotz( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByPKey == null ) {
				stmtReadBuffByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByPKey.setShort( argIdx++, ISOTZoneId );
			resultSet = stmtReadBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecISOTZoneBuff buff = unpackISOTZoneResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFSecISOTZoneBuff lockBuff( CFSecAuthorization Authorization,
		CFSecISOTZonePKey PKey )
	{
		final String S_ProcName = "lockBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			short ISOTZoneId = PKey.getRequiredISOTZoneId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_lock_isotz( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtLockBuffByPKey == null ) {
				stmtLockBuffByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtLockBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtLockBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtLockBuffByPKey.setShort( argIdx++, ISOTZoneId );
			resultSet = stmtLockBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecISOTZoneBuff buff = unpackISOTZoneResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFSecISOTZoneBuff[] readAllBuff( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_isotz_all( ?, ?, ?, ?, ? )";
			if( stmtReadAllBuff == null ) {
				stmtReadAllBuff = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadAllBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadAllBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			resultSet = stmtReadAllBuff.executeQuery();
			List<CFSecISOTZoneBuff> buffList = new LinkedList<CFSecISOTZoneBuff>();
			while( resultSet.next() ) {
				CFSecISOTZoneBuff buff = unpackISOTZoneResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecISOTZoneBuff[] retBuff = new CFSecISOTZoneBuff[ buffList.size() ];
			Iterator<CFSecISOTZoneBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFSecISOTZoneBuff readBuffByIdIdx( CFSecAuthorization Authorization,
		short ISOTZoneId )
	{
		final String S_ProcName = "readBuffByIdIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_isotz_by_ididx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByIdIdx == null ) {
				stmtReadBuffByIdIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByIdIdx.setShort( argIdx++, ISOTZoneId );
			resultSet = stmtReadBuffByIdIdx.executeQuery();
			if( resultSet.next() ) {
				CFSecISOTZoneBuff buff = unpackISOTZoneResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFSecISOTZoneBuff[] readBuffByOffsetIdx( CFSecAuthorization Authorization,
		short TZHourOffset,
		short TZMinOffset )
	{
		final String S_ProcName = "readBuffByOffsetIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_isotz_by_offsetidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByOffsetIdx == null ) {
				stmtReadBuffByOffsetIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByOffsetIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByOffsetIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByOffsetIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByOffsetIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByOffsetIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByOffsetIdx.setShort( argIdx++, TZHourOffset );
			stmtReadBuffByOffsetIdx.setShort( argIdx++, TZMinOffset );
			resultSet = stmtReadBuffByOffsetIdx.executeQuery();
			List<CFSecISOTZoneBuff> buffList = new LinkedList<CFSecISOTZoneBuff>();
			while( resultSet.next() ) {
				CFSecISOTZoneBuff buff = unpackISOTZoneResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecISOTZoneBuff[] retBuff = new CFSecISOTZoneBuff[ buffList.size() ];
			Iterator<CFSecISOTZoneBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFSecISOTZoneBuff readBuffByUTZNameIdx( CFSecAuthorization Authorization,
		String TZName )
	{
		final String S_ProcName = "readBuffByUTZNameIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_isotz_by_utznameidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByUTZNameIdx == null ) {
				stmtReadBuffByUTZNameIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByUTZNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUTZNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByUTZNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByUTZNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUTZNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByUTZNameIdx.setString( argIdx++, TZName );
			resultSet = stmtReadBuffByUTZNameIdx.executeQuery();
			if( resultSet.next() ) {
				CFSecISOTZoneBuff buff = unpackISOTZoneResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFSecISOTZoneBuff[] readBuffByIso8601Idx( CFSecAuthorization Authorization,
		String Iso8601 )
	{
		final String S_ProcName = "readBuffByIso8601Idx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_isotz_by_iso8601idx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByIso8601Idx == null ) {
				stmtReadBuffByIso8601Idx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByIso8601Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByIso8601Idx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByIso8601Idx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByIso8601Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByIso8601Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByIso8601Idx.setString( argIdx++, Iso8601 );
			resultSet = stmtReadBuffByIso8601Idx.executeQuery();
			List<CFSecISOTZoneBuff> buffList = new LinkedList<CFSecISOTZoneBuff>();
			while( resultSet.next() ) {
				CFSecISOTZoneBuff buff = unpackISOTZoneResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecISOTZoneBuff[] retBuff = new CFSecISOTZoneBuff[ buffList.size() ];
			Iterator<CFSecISOTZoneBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void updateISOTZone( CFSecAuthorization Authorization,
		CFSecISOTZoneBuff Buff )
	{
		final String S_ProcName = "updateISOTZone";
		ResultSet resultSet = null;
		try {
			short ISOTZoneId = Buff.getRequiredISOTZoneId();
			String Iso8601 = Buff.getRequiredIso8601();
			String TZName = Buff.getRequiredTZName();
			short TZHourOffset = Buff.getRequiredTZHourOffset();
			short TZMinOffset = Buff.getRequiredTZMinOffset();
			String Description = Buff.getRequiredDescription();
			boolean Visible = Buff.getRequiredVisible();
			int Revision = Buff.getRequiredRevision();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_update_isotz( ?, ?, ?, ?, ?, ?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + " )";
			if( stmtUpdateByPKey == null ) {
				stmtUpdateByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtUpdateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtUpdateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtUpdateByPKey.setString( argIdx++, "a008" );
			stmtUpdateByPKey.setShort( argIdx++, ISOTZoneId );
			stmtUpdateByPKey.setString( argIdx++, Iso8601 );
			stmtUpdateByPKey.setString( argIdx++, TZName );
			stmtUpdateByPKey.setShort( argIdx++, TZHourOffset );
			stmtUpdateByPKey.setShort( argIdx++, TZMinOffset );
			stmtUpdateByPKey.setString( argIdx++, Description );
			stmtUpdateByPKey.setBoolean( argIdx++, Visible );
			stmtUpdateByPKey.setInt( argIdx++, Revision );
			resultSet = stmtUpdateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecISOTZoneBuff updatedBuff = unpackISOTZoneResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredIso8601( updatedBuff.getRequiredIso8601() );
				Buff.setRequiredTZName( updatedBuff.getRequiredTZName() );
				Buff.setRequiredTZHourOffset( updatedBuff.getRequiredTZHourOffset() );
				Buff.setRequiredTZMinOffset( updatedBuff.getRequiredTZMinOffset() );
				Buff.setRequiredDescription( updatedBuff.getRequiredDescription() );
				Buff.setRequiredVisible( updatedBuff.getRequiredVisible() );
				Buff.setRequiredRevision( updatedBuff.getRequiredRevision() );
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected a single-record response, " + resultSet.getRow() + " rows selected" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteISOTZone( CFSecAuthorization Authorization,
		CFSecISOTZoneBuff Buff )
	{
		final String S_ProcName = "deleteISOTZone";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			short ISOTZoneId = Buff.getRequiredISOTZoneId();

			String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_isotz( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
			if( stmtDeleteByPKey == null ) {
				stmtDeleteByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtDeleteByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtDeleteByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtDeleteByPKey.setShort( argIdx++, ISOTZoneId );
			stmtDeleteByPKey.setInt( argIdx++, Buff.getRequiredRevision() );;
			resultSet = stmtDeleteByPKey.executeQuery();
			if( resultSet.next() ) {
				boolean deleteFlag = resultSet.getBoolean( 1 );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 record result set to be returned by delete, not 0 rows" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteISOTZoneByIdIdx( CFSecAuthorization Authorization,
		short argISOTZoneId )
	{
		final String S_ProcName = "deleteISOTZoneByIdIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_isotz_by_ididx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByIdIdx == null ) {
					stmtDeleteByIdIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByIdIdx.setShort( argIdx++, argISOTZoneId );
				resultSet = stmtDeleteByIdIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
			}
			catch( SQLException e ) {
				throw new CFLibDbException( getClass(),
					S_ProcName,
					e );
			}
			finally {
				if( resultSet != null ) {
					try {
						resultSet.close();
					}
					catch( SQLException e ) {
					}
					resultSet = null;
				}
			}
	}

	public void deleteISOTZoneByIdIdx( CFSecAuthorization Authorization,
		CFSecISOTZonePKey argKey )
	{
		deleteISOTZoneByIdIdx( Authorization,
			argKey.getRequiredISOTZoneId() );
	}

	public void deleteISOTZoneByOffsetIdx( CFSecAuthorization Authorization,
		short argTZHourOffset,
		short argTZMinOffset )
	{
		final String S_ProcName = "deleteISOTZoneByOffsetIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_isotz_by_offsetidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByOffsetIdx == null ) {
					stmtDeleteByOffsetIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByOffsetIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByOffsetIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByOffsetIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByOffsetIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByOffsetIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByOffsetIdx.setShort( argIdx++, argTZHourOffset );
				stmtDeleteByOffsetIdx.setShort( argIdx++, argTZMinOffset );
				resultSet = stmtDeleteByOffsetIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteISOTZoneByOffsetIdx( CFSecAuthorization Authorization,
		CFSecISOTZoneByOffsetIdxKey argKey )
	{
		deleteISOTZoneByOffsetIdx( Authorization,
			argKey.getRequiredTZHourOffset(),
			argKey.getRequiredTZMinOffset() );
	}

	public void deleteISOTZoneByUTZNameIdx( CFSecAuthorization Authorization,
		String argTZName )
	{
		final String S_ProcName = "deleteISOTZoneByUTZNameIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_isotz_by_utznameidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByUTZNameIdx == null ) {
					stmtDeleteByUTZNameIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByUTZNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUTZNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByUTZNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByUTZNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUTZNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByUTZNameIdx.setString( argIdx++, argTZName );
				resultSet = stmtDeleteByUTZNameIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteISOTZoneByUTZNameIdx( CFSecAuthorization Authorization,
		CFSecISOTZoneByUTZNameIdxKey argKey )
	{
		deleteISOTZoneByUTZNameIdx( Authorization,
			argKey.getRequiredTZName() );
	}

	public void deleteISOTZoneByIso8601Idx( CFSecAuthorization Authorization,
		String argIso8601 )
	{
		final String S_ProcName = "deleteISOTZoneByIso8601Idx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_isotz_by_iso8601idx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByIso8601Idx == null ) {
					stmtDeleteByIso8601Idx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByIso8601Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByIso8601Idx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByIso8601Idx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByIso8601Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByIso8601Idx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByIso8601Idx.setString( argIdx++, argIso8601 );
				resultSet = stmtDeleteByIso8601Idx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteISOTZoneByIso8601Idx( CFSecAuthorization Authorization,
		CFSecISOTZoneByIso8601IdxKey argKey )
	{
		deleteISOTZoneByIso8601Idx( Authorization,
			argKey.getRequiredIso8601() );
	}

	/**
	 *	Release the prepared statements.
	 *	<p>
	 *	When the schema changes connections, the prepared statements
	 *	have to be released because they contain connection-specific
	 *	information for most databases.
	 */
	public void releasePreparedStatements() {
		final String S_ProcName = "releasePreparedStatements";
		S_sqlSelectISOTZoneDistinctClassCode = null;
		S_sqlSelectISOTZoneBuff = null;

		if( stmtReadBuffByPKey != null ) {
			try {
				stmtReadBuffByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByPKey = null;
		}
		if( stmtReadBuffAll != null ) {
			try {
				stmtReadBuffAll.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffAll = null;
		}
		if( stmtLockBuffByPKey != null ) {
			try {
				stmtLockBuffByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtLockBuffByPKey = null;
		}
		if( stmtCreateByPKey != null ) {
			try {
				stmtCreateByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtCreateByPKey = null;
		}
		if( stmtUpdateByPKey != null ) {
			try {
				stmtUpdateByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtUpdateByPKey = null;
		}
		if( stmtDeleteByPKey != null ) {
			try {
				stmtDeleteByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByPKey = null;
		}
		if( stmtDeleteByIdIdx != null ) {
			try {
				stmtDeleteByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByIdIdx = null;
		}
		if( stmtDeleteByOffsetIdx != null ) {
			try {
				stmtDeleteByOffsetIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByOffsetIdx = null;
		}
		if( stmtDeleteByUTZNameIdx != null ) {
			try {
				stmtDeleteByUTZNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByUTZNameIdx = null;
		}
		if( stmtDeleteByIso8601Idx != null ) {
			try {
				stmtDeleteByIso8601Idx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByIso8601Idx = null;
		}
		if( stmtReadAllBuff != null ) {
			try {
				stmtReadAllBuff.close();
			}
			catch( SQLException e ) {
			}
			stmtReadAllBuff = null;
		}
		if( stmtReadBuffByIdIdx != null ) {
			try {
				stmtReadBuffByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByIdIdx = null;
		}
		if( stmtReadBuffByOffsetIdx != null ) {
			try {
				stmtReadBuffByOffsetIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByOffsetIdx = null;
		}
		if( stmtReadBuffByUTZNameIdx != null ) {
			try {
				stmtReadBuffByUTZNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByUTZNameIdx = null;
		}
		if( stmtReadBuffByIso8601Idx != null ) {
			try {
				stmtReadBuffByIso8601Idx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByIso8601Idx = null;
		}
	}
}
