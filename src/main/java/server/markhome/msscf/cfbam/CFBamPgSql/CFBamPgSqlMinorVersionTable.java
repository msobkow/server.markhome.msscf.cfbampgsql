// Description: Java 11 PostgreSQL Jdbc DbIO implementation for MinorVersion.

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
 *	CFBamPgSqlMinorVersionTable PostgreSQL Jdbc DbIO implementation
 *	for MinorVersion.
 */
public class CFBamPgSqlMinorVersionTable
	implements ICFBamMinorVersionTable
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
	protected PreparedStatement stmtReadBuffByTenantIdx = null;
	protected PreparedStatement stmtReadBuffByMajorVerIdx = null;
	protected PreparedStatement stmtReadBuffByNameIdx = null;
	protected PreparedStatement stmtDeleteByIdIdx = null;
	protected PreparedStatement stmtDeleteByTenantIdx = null;
	protected PreparedStatement stmtDeleteByMajorVerIdx = null;
	protected PreparedStatement stmtDeleteByNameIdx = null;

	public CFBamPgSqlMinorVersionTable( CFBamPgSqlSchema argSchema ) {
		schema = argSchema;
	}

	public void createMinorVersion( CFSecAuthorization Authorization,
		CFIntMinorVersionBuff Buff )
	{
		final String S_ProcName = "createMinorVersion";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			long TenantId = Buff.getRequiredTenantId();
			long MajorVersionId = Buff.getRequiredMajorVersionId();
			String Name = Buff.getRequiredName();
			String Description = Buff.getOptionalDescription();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_create_mnvrdef( ?, ?, ?, ?, ?, ?" + ", "
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
			stmtCreateByPKey.setString( argIdx++, "a104" );
			stmtCreateByPKey.setLong( argIdx++, TenantId );
			stmtCreateByPKey.setLong( argIdx++, MajorVersionId );
			stmtCreateByPKey.setString( argIdx++, Name );
			if( Description != null ) {
				stmtCreateByPKey.setString( argIdx++, Description );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			resultSet = stmtCreateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFIntMinorVersionBuff createdBuff = unpackMinorVersionResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredTenantId( createdBuff.getRequiredTenantId() );
				Buff.setRequiredId( createdBuff.getRequiredId() );
				Buff.setRequiredMajorVersionId( createdBuff.getRequiredMajorVersionId() );
				Buff.setRequiredName( createdBuff.getRequiredName() );
				Buff.setOptionalDescription( createdBuff.getOptionalDescription() );
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

	public String S_sqlSelectMinorVersionDistinctClassCode = null;

	public String getSqlSelectMinorVersionDistinctClassCode() {
		if( S_sqlSelectMinorVersionDistinctClassCode == null ) {
			S_sqlSelectMinorVersionDistinctClassCode =
					"SELECT "
					+		"DISTINCT a104.ClassCode "
					+	"FROM " + schema.getLowerDbSchemaName() + ".mnvrdef AS a104 ";
		}
		return( S_sqlSelectMinorVersionDistinctClassCode );
	}

	public String S_sqlSelectMinorVersionBuff = null;

	public String getSqlSelectMinorVersionBuff() {
		if( S_sqlSelectMinorVersionBuff == null ) {
			S_sqlSelectMinorVersionBuff =
					"SELECT "
					+		"a104.TenantId, "
					+		"a104.Id, "
					+		"a104.MajorVersionId, "
					+		"a104.safe_name, "
					+		"a104.descr, "
					+		"a104.Revision "
				+	"FROM " + schema.getLowerDbSchemaName() + ".mnvrdef AS a104 ";
		}
		return( S_sqlSelectMinorVersionBuff );
	}

	protected CFIntMinorVersionBuff unpackMinorVersionResultSetToBuff( ResultSet resultSet )
	throws SQLException
	{
		final String S_ProcName = "unpackMinorVersionResultSetToBuff";
		int idxcol = 1;
		CFIntMinorVersionBuff buff = schema.getFactoryMinorVersion().newBuff();
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
		buff.setRequiredTenantId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredMajorVersionId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredName( resultSet.getString( idxcol ) );
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalDescription( null );
			}
			else {
				buff.setOptionalDescription( colVal );
			}
		}
		idxcol++;

		buff.setRequiredRevision( resultSet.getInt( idxcol ) );
		return( buff );
	}

	public CFIntMinorVersionBuff readDerived( CFSecAuthorization Authorization,
		CFIntMinorVersionPKey PKey )
	{
		final String S_ProcName = "readDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFIntMinorVersionBuff buff;

		buff = readBuff( Authorization, PKey );
		return( buff );
	}

	public CFIntMinorVersionBuff lockDerived( CFSecAuthorization Authorization,
		CFIntMinorVersionPKey PKey )
	{
		final String S_ProcName = "lockDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFIntMinorVersionBuff buff;

		buff = lockBuff( Authorization, PKey );
		return( buff );
	}

	public CFIntMinorVersionBuff[] readAllDerived( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllDerived";
		CFIntMinorVersionBuff[] buffArray;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buffArray = readAllBuff( Authorization );
		return( buffArray );
	}

	public CFIntMinorVersionBuff readDerivedByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "CFBamPgSqlMinorVersionTable.readDerivedByIdIdx() ";
		CFIntMinorVersionBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		return( buff );
	}

	public CFIntMinorVersionBuff[] readDerivedByTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readDerivedByTenantIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFIntMinorVersionBuff[] buffList = readBuffByTenantIdx( Authorization,
				TenantId );
		return( buffList );

	}

	public CFIntMinorVersionBuff[] readDerivedByMajorVerIdx( CFSecAuthorization Authorization,
		long TenantId,
		long MajorVersionId )
	{
		final String S_ProcName = "readDerivedByMajorVerIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFIntMinorVersionBuff[] buffList = readBuffByMajorVerIdx( Authorization,
				TenantId,
				MajorVersionId );
		return( buffList );

	}

	public CFIntMinorVersionBuff readDerivedByNameIdx( CFSecAuthorization Authorization,
		long TenantId,
		long MajorVersionId,
		String Name )
	{
		final String S_ProcName = "CFBamPgSqlMinorVersionTable.readDerivedByNameIdx() ";
		CFIntMinorVersionBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByNameIdx( Authorization,
				TenantId,
				MajorVersionId,
				Name );
		return( buff );
	}

	public CFIntMinorVersionBuff readBuff( CFSecAuthorization Authorization,
		CFIntMinorVersionPKey PKey )
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
			long TenantId = PKey.getRequiredTenantId();
			long Id = PKey.getRequiredId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_mnvrdef( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
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
			stmtReadBuffByPKey.setLong( argIdx++, TenantId );
			stmtReadBuffByPKey.setLong( argIdx++, Id );
			resultSet = stmtReadBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFIntMinorVersionBuff buff = unpackMinorVersionResultSetToBuff( resultSet );
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

	public CFIntMinorVersionBuff lockBuff( CFSecAuthorization Authorization,
		CFIntMinorVersionPKey PKey )
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
			long TenantId = PKey.getRequiredTenantId();
			long Id = PKey.getRequiredId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_lock_mnvrdef( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
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
			stmtLockBuffByPKey.setLong( argIdx++, TenantId );
			stmtLockBuffByPKey.setLong( argIdx++, Id );
			resultSet = stmtLockBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFIntMinorVersionBuff buff = unpackMinorVersionResultSetToBuff( resultSet );
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

	public CFIntMinorVersionBuff[] readAllBuff( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_mnvrdef_all( ?, ?, ?, ?, ? )";
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
			List<CFIntMinorVersionBuff> buffList = new LinkedList<CFIntMinorVersionBuff>();
			while( resultSet.next() ) {
				CFIntMinorVersionBuff buff = unpackMinorVersionResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFIntMinorVersionBuff[] retBuff = new CFIntMinorVersionBuff[ buffList.size() ];
			Iterator<CFIntMinorVersionBuff> iter = buffList.iterator();
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

	public CFIntMinorVersionBuff readBuffByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "readBuffByIdIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_mnvrdef_by_ididx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
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
			stmtReadBuffByIdIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByIdIdx.setLong( argIdx++, Id );
			resultSet = stmtReadBuffByIdIdx.executeQuery();
			if( resultSet.next() ) {
				CFIntMinorVersionBuff buff = unpackMinorVersionResultSetToBuff( resultSet );
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

	public CFIntMinorVersionBuff[] readBuffByTenantIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readBuffByTenantIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_mnvrdef_by_tenantidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByTenantIdx == null ) {
				stmtReadBuffByTenantIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByTenantIdx.setLong( argIdx++, TenantId );
			resultSet = stmtReadBuffByTenantIdx.executeQuery();
			List<CFIntMinorVersionBuff> buffList = new LinkedList<CFIntMinorVersionBuff>();
			while( resultSet.next() ) {
				CFIntMinorVersionBuff buff = unpackMinorVersionResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFIntMinorVersionBuff[] retBuff = new CFIntMinorVersionBuff[ buffList.size() ];
			Iterator<CFIntMinorVersionBuff> iter = buffList.iterator();
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

	public CFIntMinorVersionBuff[] readBuffByMajorVerIdx( CFSecAuthorization Authorization,
		long TenantId,
		long MajorVersionId )
	{
		final String S_ProcName = "readBuffByMajorVerIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_mnvrdef_by_majorveridx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByMajorVerIdx == null ) {
				stmtReadBuffByMajorVerIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByMajorVerIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByMajorVerIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByMajorVerIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByMajorVerIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByMajorVerIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByMajorVerIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByMajorVerIdx.setLong( argIdx++, MajorVersionId );
			resultSet = stmtReadBuffByMajorVerIdx.executeQuery();
			List<CFIntMinorVersionBuff> buffList = new LinkedList<CFIntMinorVersionBuff>();
			while( resultSet.next() ) {
				CFIntMinorVersionBuff buff = unpackMinorVersionResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFIntMinorVersionBuff[] retBuff = new CFIntMinorVersionBuff[ buffList.size() ];
			Iterator<CFIntMinorVersionBuff> iter = buffList.iterator();
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

	public CFIntMinorVersionBuff readBuffByNameIdx( CFSecAuthorization Authorization,
		long TenantId,
		long MajorVersionId,
		String Name )
	{
		final String S_ProcName = "readBuffByNameIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_mnvrdef_by_nameidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByNameIdx == null ) {
				stmtReadBuffByNameIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByNameIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByNameIdx.setLong( argIdx++, MajorVersionId );
			stmtReadBuffByNameIdx.setString( argIdx++, Name );
			resultSet = stmtReadBuffByNameIdx.executeQuery();
			if( resultSet.next() ) {
				CFIntMinorVersionBuff buff = unpackMinorVersionResultSetToBuff( resultSet );
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

	public void updateMinorVersion( CFSecAuthorization Authorization,
		CFIntMinorVersionBuff Buff )
	{
		final String S_ProcName = "updateMinorVersion";
		ResultSet resultSet = null;
		try {
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();
			long MajorVersionId = Buff.getRequiredMajorVersionId();
			String Name = Buff.getRequiredName();
			String Description = Buff.getOptionalDescription();
			int Revision = Buff.getRequiredRevision();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_update_mnvrdef( ?, ?, ?, ?, ?, ?" + ", "
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
			stmtUpdateByPKey.setString( argIdx++, "a104" );
			stmtUpdateByPKey.setLong( argIdx++, TenantId );
			stmtUpdateByPKey.setLong( argIdx++, Id );
			stmtUpdateByPKey.setLong( argIdx++, MajorVersionId );
			stmtUpdateByPKey.setString( argIdx++, Name );
			if( Description != null ) {
				stmtUpdateByPKey.setString( argIdx++, Description );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			stmtUpdateByPKey.setInt( argIdx++, Revision );
			resultSet = stmtUpdateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFIntMinorVersionBuff updatedBuff = unpackMinorVersionResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredMajorVersionId( updatedBuff.getRequiredMajorVersionId() );
				Buff.setRequiredName( updatedBuff.getRequiredName() );
				Buff.setOptionalDescription( updatedBuff.getOptionalDescription() );
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

	public void deleteMinorVersion( CFSecAuthorization Authorization,
		CFIntMinorVersionBuff Buff )
	{
		final String S_ProcName = "deleteMinorVersion";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();

			String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_mnvrdef( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
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
			stmtDeleteByPKey.setLong( argIdx++, TenantId );
			stmtDeleteByPKey.setLong( argIdx++, Id );
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

	public void deleteMinorVersionByIdIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argId )
	{
		final String S_ProcName = "deleteMinorVersionByIdIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_mnvrdef_by_ididx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
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
				stmtDeleteByIdIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByIdIdx.setLong( argIdx++, argId );
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

	public void deleteMinorVersionByIdIdx( CFSecAuthorization Authorization,
		CFIntMinorVersionPKey argKey )
	{
		deleteMinorVersionByIdIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredId() );
	}

	public void deleteMinorVersionByTenantIdx( CFSecAuthorization Authorization,
		long argTenantId )
	{
		final String S_ProcName = "deleteMinorVersionByTenantIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_mnvrdef_by_tenantidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByTenantIdx == null ) {
					stmtDeleteByTenantIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByTenantIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByTenantIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByTenantIdx.setLong( argIdx++, argTenantId );
				resultSet = stmtDeleteByTenantIdx.executeQuery();
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

	public void deleteMinorVersionByTenantIdx( CFSecAuthorization Authorization,
		CFIntMinorVersionByTenantIdxKey argKey )
	{
		deleteMinorVersionByTenantIdx( Authorization,
			argKey.getRequiredTenantId() );
	}

	public void deleteMinorVersionByMajorVerIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argMajorVersionId )
	{
		final String S_ProcName = "deleteMinorVersionByMajorVerIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_mnvrdef_by_majorveridx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByMajorVerIdx == null ) {
					stmtDeleteByMajorVerIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByMajorVerIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByMajorVerIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByMajorVerIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByMajorVerIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByMajorVerIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByMajorVerIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByMajorVerIdx.setLong( argIdx++, argMajorVersionId );
				resultSet = stmtDeleteByMajorVerIdx.executeQuery();
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

	public void deleteMinorVersionByMajorVerIdx( CFSecAuthorization Authorization,
		CFIntMinorVersionByMajorVerIdxKey argKey )
	{
		deleteMinorVersionByMajorVerIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredMajorVersionId() );
	}

	public void deleteMinorVersionByNameIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argMajorVersionId,
		String argName )
	{
		final String S_ProcName = "deleteMinorVersionByNameIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_mnvrdef_by_nameidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByNameIdx == null ) {
					stmtDeleteByNameIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByNameIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByNameIdx.setLong( argIdx++, argMajorVersionId );
				stmtDeleteByNameIdx.setString( argIdx++, argName );
				resultSet = stmtDeleteByNameIdx.executeQuery();
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

	public void deleteMinorVersionByNameIdx( CFSecAuthorization Authorization,
		CFIntMinorVersionByNameIdxKey argKey )
	{
		deleteMinorVersionByNameIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredMajorVersionId(),
			argKey.getRequiredName() );
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
		S_sqlSelectMinorVersionDistinctClassCode = null;
		S_sqlSelectMinorVersionBuff = null;

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
		if( stmtDeleteByTenantIdx != null ) {
			try {
				stmtDeleteByTenantIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByTenantIdx = null;
		}
		if( stmtDeleteByMajorVerIdx != null ) {
			try {
				stmtDeleteByMajorVerIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByMajorVerIdx = null;
		}
		if( stmtDeleteByNameIdx != null ) {
			try {
				stmtDeleteByNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByNameIdx = null;
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
		if( stmtReadBuffByTenantIdx != null ) {
			try {
				stmtReadBuffByTenantIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByTenantIdx = null;
		}
		if( stmtReadBuffByMajorVerIdx != null ) {
			try {
				stmtReadBuffByMajorVerIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByMajorVerIdx = null;
		}
		if( stmtReadBuffByNameIdx != null ) {
			try {
				stmtReadBuffByNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByNameIdx = null;
		}
	}
}
