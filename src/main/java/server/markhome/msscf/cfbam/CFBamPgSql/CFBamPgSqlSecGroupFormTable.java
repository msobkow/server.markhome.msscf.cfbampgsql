// Description: Java 11 PostgreSQL Jdbc DbIO implementation for SecGroupForm.

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
 *	CFBamPgSqlSecGroupFormTable PostgreSQL Jdbc DbIO implementation
 *	for SecGroupForm.
 */
public class CFBamPgSqlSecGroupFormTable
	implements ICFBamSecGroupFormTable
{
	private CFBamPgSqlSchema schema;
	protected PreparedStatement stmtReadBuffByPKey = null;
	protected PreparedStatement stmtReadBuffAll = null;
	protected PreparedStatement stmtPageBuffAll = null;
	protected PreparedStatement stmtLockBuffByPKey = null;
	protected PreparedStatement stmtCreateByPKey = null;
	protected PreparedStatement stmtUpdateByPKey = null;
	protected PreparedStatement stmtDeleteByPKey = null;
	protected PreparedStatement stmtReadAllBuff = null;
	protected PreparedStatement stmtReadBuffByIdIdx = null;
	protected PreparedStatement stmtReadBuffByClusterIdx = null;
	protected PreparedStatement stmtReadBuffByGroupIdx = null;
	protected PreparedStatement stmtReadBuffByAppIdx = null;
	protected PreparedStatement stmtReadBuffByFormIdx = null;
	protected PreparedStatement stmtReadBuffByUFormIdx = null;
	protected PreparedStatement stmtPageBuffByClusterIdx = null;
	protected PreparedStatement stmtPageBuffByGroupIdx = null;
	protected PreparedStatement stmtPageBuffByAppIdx = null;
	protected PreparedStatement stmtPageBuffByFormIdx = null;
	protected PreparedStatement stmtDeleteByIdIdx = null;
	protected PreparedStatement stmtDeleteByClusterIdx = null;
	protected PreparedStatement stmtDeleteByGroupIdx = null;
	protected PreparedStatement stmtDeleteByAppIdx = null;
	protected PreparedStatement stmtDeleteByFormIdx = null;
	protected PreparedStatement stmtDeleteByUFormIdx = null;

	public CFBamPgSqlSecGroupFormTable( CFBamPgSqlSchema argSchema ) {
		schema = argSchema;
	}

	public void createSecGroupForm( CFSecAuthorization Authorization,
		CFSecSecGroupFormBuff Buff )
	{
		final String S_ProcName = "createSecGroupForm";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			long ClusterId = Buff.getRequiredClusterId();
			int SecGroupId = Buff.getRequiredSecGroupId();
			int SecAppId = Buff.getRequiredSecAppId();
			int SecFormId = Buff.getRequiredSecFormId();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_create_secgrpfrm( ?, ?, ?, ?, ?, ?" + ", "
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
			stmtCreateByPKey.setString( argIdx++, "a00d" );
			stmtCreateByPKey.setLong( argIdx++, ClusterId );
			stmtCreateByPKey.setInt( argIdx++, SecGroupId );
			stmtCreateByPKey.setInt( argIdx++, SecAppId );
			stmtCreateByPKey.setInt( argIdx++, SecFormId );
			resultSet = stmtCreateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecSecGroupFormBuff createdBuff = unpackSecGroupFormResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredClusterId( createdBuff.getRequiredClusterId() );
				Buff.setRequiredSecGroupFormId( createdBuff.getRequiredSecGroupFormId() );
				Buff.setRequiredSecGroupId( createdBuff.getRequiredSecGroupId() );
				Buff.setRequiredSecAppId( createdBuff.getRequiredSecAppId() );
				Buff.setRequiredSecFormId( createdBuff.getRequiredSecFormId() );
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

	public String S_sqlSelectSecGroupFormDistinctClassCode = null;

	public String getSqlSelectSecGroupFormDistinctClassCode() {
		if( S_sqlSelectSecGroupFormDistinctClassCode == null ) {
			S_sqlSelectSecGroupFormDistinctClassCode =
					"SELECT "
					+		"DISTINCT a00d.ClassCode "
					+	"FROM " + schema.getLowerDbSchemaName() + ".SecGrpFrm AS a00d ";
		}
		return( S_sqlSelectSecGroupFormDistinctClassCode );
	}

	public String S_sqlSelectSecGroupFormBuff = null;

	public String getSqlSelectSecGroupFormBuff() {
		if( S_sqlSelectSecGroupFormBuff == null ) {
			S_sqlSelectSecGroupFormBuff =
					"SELECT "
					+		"a00d.ClusId, "
					+		"a00d.SecGrpFrmId, "
					+		"a00d.SecGrpId, "
					+		"a00d.SecAppId, "
					+		"a00d.SecFrmId, "
					+		"a00d.Revision "
				+	"FROM " + schema.getLowerDbSchemaName() + ".SecGrpFrm AS a00d ";
		}
		return( S_sqlSelectSecGroupFormBuff );
	}

	protected CFSecSecGroupFormBuff unpackSecGroupFormResultSetToBuff( ResultSet resultSet )
	throws SQLException
	{
		final String S_ProcName = "unpackSecGroupFormResultSetToBuff";
		int idxcol = 1;
		CFSecSecGroupFormBuff buff = schema.getFactorySecGroupForm().newBuff();
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
		buff.setRequiredClusterId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredSecGroupFormId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredSecGroupId( resultSet.getInt( idxcol ) );
		idxcol++;
		buff.setRequiredSecAppId( resultSet.getInt( idxcol ) );
		idxcol++;
		buff.setRequiredSecFormId( resultSet.getInt( idxcol ) );
		idxcol++;

		buff.setRequiredRevision( resultSet.getInt( idxcol ) );
		return( buff );
	}

	public CFSecSecGroupFormBuff readDerived( CFSecAuthorization Authorization,
		CFSecSecGroupFormPKey PKey )
	{
		final String S_ProcName = "readDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecSecGroupFormBuff buff;

		buff = readBuff( Authorization, PKey );
		return( buff );
	}

	public CFSecSecGroupFormBuff lockDerived( CFSecAuthorization Authorization,
		CFSecSecGroupFormPKey PKey )
	{
		final String S_ProcName = "lockDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecSecGroupFormBuff buff;

		buff = lockBuff( Authorization, PKey );
		return( buff );
	}

	public CFSecSecGroupFormBuff[] readAllDerived( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllDerived";
		CFSecSecGroupFormBuff[] buffArray;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buffArray = readAllBuff( Authorization );
		return( buffArray );
	}

	public CFSecSecGroupFormBuff readDerivedByIdIdx( CFSecAuthorization Authorization,
		long ClusterId,
		long SecGroupFormId )
	{
		final String S_ProcName = "CFBamPgSqlSecGroupFormTable.readDerivedByIdIdx() ";
		CFSecSecGroupFormBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByIdIdx( Authorization,
				ClusterId,
				SecGroupFormId );
		return( buff );
	}

	public CFSecSecGroupFormBuff[] readDerivedByClusterIdx( CFSecAuthorization Authorization,
		long ClusterId )
	{
		final String S_ProcName = "readDerivedByClusterIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecSecGroupFormBuff[] buffList = readBuffByClusterIdx( Authorization,
				ClusterId );
		return( buffList );

	}

	public CFSecSecGroupFormBuff[] readDerivedByGroupIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecGroupId )
	{
		final String S_ProcName = "readDerivedByGroupIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecSecGroupFormBuff[] buffList = readBuffByGroupIdx( Authorization,
				ClusterId,
				SecGroupId );
		return( buffList );

	}

	public CFSecSecGroupFormBuff[] readDerivedByAppIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecAppId )
	{
		final String S_ProcName = "readDerivedByAppIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecSecGroupFormBuff[] buffList = readBuffByAppIdx( Authorization,
				ClusterId,
				SecAppId );
		return( buffList );

	}

	public CFSecSecGroupFormBuff[] readDerivedByFormIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecFormId )
	{
		final String S_ProcName = "readDerivedByFormIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecSecGroupFormBuff[] buffList = readBuffByFormIdx( Authorization,
				ClusterId,
				SecFormId );
		return( buffList );

	}

	public CFSecSecGroupFormBuff readDerivedByUFormIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecGroupId,
		int SecFormId )
	{
		final String S_ProcName = "CFBamPgSqlSecGroupFormTable.readDerivedByUFormIdx() ";
		CFSecSecGroupFormBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByUFormIdx( Authorization,
				ClusterId,
				SecGroupId,
				SecFormId );
		return( buff );
	}

	public CFSecSecGroupFormBuff readBuff( CFSecAuthorization Authorization,
		CFSecSecGroupFormPKey PKey )
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
			long ClusterId = PKey.getRequiredClusterId();
			long SecGroupFormId = PKey.getRequiredSecGroupFormId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secgrpfrm( ?, ?, ?, ?, ?" + ", "
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
			stmtReadBuffByPKey.setLong( argIdx++, ClusterId );
			stmtReadBuffByPKey.setLong( argIdx++, SecGroupFormId );
			resultSet = stmtReadBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecSecGroupFormBuff buff = unpackSecGroupFormResultSetToBuff( resultSet );
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

	public CFSecSecGroupFormBuff lockBuff( CFSecAuthorization Authorization,
		CFSecSecGroupFormPKey PKey )
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
			long ClusterId = PKey.getRequiredClusterId();
			long SecGroupFormId = PKey.getRequiredSecGroupFormId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_lock_secgrpfrm( ?, ?, ?, ?, ?" + ", "
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
			stmtLockBuffByPKey.setLong( argIdx++, ClusterId );
			stmtLockBuffByPKey.setLong( argIdx++, SecGroupFormId );
			resultSet = stmtLockBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecSecGroupFormBuff buff = unpackSecGroupFormResultSetToBuff( resultSet );
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

	public CFSecSecGroupFormBuff[] readAllBuff( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secgrpfrm_all( ?, ?, ?, ?, ? )";
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
			List<CFSecSecGroupFormBuff> buffList = new LinkedList<CFSecSecGroupFormBuff>();
			while( resultSet.next() ) {
				CFSecSecGroupFormBuff buff = unpackSecGroupFormResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecGroupFormBuff[] retBuff = new CFSecSecGroupFormBuff[ buffList.size() ];
			Iterator<CFSecSecGroupFormBuff> iter = buffList.iterator();
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

	/**
	 *	Read a page of all the specific SecGroupForm buffer instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific SecGroupForm instances in the database accessible for the Authorization.
	 */
	public CFSecSecGroupFormBuff[] pageAllBuff( CFSecAuthorization Authorization,
		Long priorClusterId,
		Long priorSecGroupFormId )
	{
		final String S_ProcName = "pageAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_secgrpfrm_all( ?, ?, ?, ?, ?, ?, ? )";
			if( stmtPageBuffAll == null ) {
				stmtPageBuffAll = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtPageBuffAll.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffAll.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtPageBuffAll.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtPageBuffAll.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffAll.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( priorClusterId != null ) {
				stmtPageBuffAll.setLong( argIdx++, priorClusterId.longValue() );
			}
			else {
				stmtPageBuffAll.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( priorSecGroupFormId != null ) {
				stmtPageBuffAll.setLong( argIdx++, priorSecGroupFormId.longValue() );
			}
			else {
				stmtPageBuffAll.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtPageBuffAll.executeQuery();
			List<CFSecSecGroupFormBuff> buffList = new LinkedList<CFSecSecGroupFormBuff>();
			while( resultSet.next() ) {
				CFSecSecGroupFormBuff buff = unpackSecGroupFormResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecGroupFormBuff[] retBuff = new CFSecSecGroupFormBuff[ buffList.size() ];
			Iterator<CFSecSecGroupFormBuff> iter = buffList.iterator();
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

	public CFSecSecGroupFormBuff readBuffByIdIdx( CFSecAuthorization Authorization,
		long ClusterId,
		long SecGroupFormId )
	{
		final String S_ProcName = "readBuffByIdIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secgrpfrm_by_ididx( ?, ?, ?, ?, ?" + ", "
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
			stmtReadBuffByIdIdx.setLong( argIdx++, ClusterId );
			stmtReadBuffByIdIdx.setLong( argIdx++, SecGroupFormId );
			resultSet = stmtReadBuffByIdIdx.executeQuery();
			if( resultSet.next() ) {
				CFSecSecGroupFormBuff buff = unpackSecGroupFormResultSetToBuff( resultSet );
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

	public CFSecSecGroupFormBuff[] readBuffByClusterIdx( CFSecAuthorization Authorization,
		long ClusterId )
	{
		final String S_ProcName = "readBuffByClusterIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secgrpfrm_by_clusteridx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByClusterIdx == null ) {
				stmtReadBuffByClusterIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByClusterIdx.setLong( argIdx++, ClusterId );
			resultSet = stmtReadBuffByClusterIdx.executeQuery();
			List<CFSecSecGroupFormBuff> buffList = new LinkedList<CFSecSecGroupFormBuff>();
			while( resultSet.next() ) {
				CFSecSecGroupFormBuff buff = unpackSecGroupFormResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecGroupFormBuff[] retBuff = new CFSecSecGroupFormBuff[ buffList.size() ];
			Iterator<CFSecSecGroupFormBuff> iter = buffList.iterator();
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

	public CFSecSecGroupFormBuff[] readBuffByGroupIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecGroupId )
	{
		final String S_ProcName = "readBuffByGroupIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secgrpfrm_by_groupidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByGroupIdx == null ) {
				stmtReadBuffByGroupIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByGroupIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByGroupIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByGroupIdx.setLong( argIdx++, ClusterId );
			stmtReadBuffByGroupIdx.setInt( argIdx++, SecGroupId );
			resultSet = stmtReadBuffByGroupIdx.executeQuery();
			List<CFSecSecGroupFormBuff> buffList = new LinkedList<CFSecSecGroupFormBuff>();
			while( resultSet.next() ) {
				CFSecSecGroupFormBuff buff = unpackSecGroupFormResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecGroupFormBuff[] retBuff = new CFSecSecGroupFormBuff[ buffList.size() ];
			Iterator<CFSecSecGroupFormBuff> iter = buffList.iterator();
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

	public CFSecSecGroupFormBuff[] readBuffByAppIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecAppId )
	{
		final String S_ProcName = "readBuffByAppIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secgrpfrm_by_appidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByAppIdx == null ) {
				stmtReadBuffByAppIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByAppIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByAppIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByAppIdx.setLong( argIdx++, ClusterId );
			stmtReadBuffByAppIdx.setInt( argIdx++, SecAppId );
			resultSet = stmtReadBuffByAppIdx.executeQuery();
			List<CFSecSecGroupFormBuff> buffList = new LinkedList<CFSecSecGroupFormBuff>();
			while( resultSet.next() ) {
				CFSecSecGroupFormBuff buff = unpackSecGroupFormResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecGroupFormBuff[] retBuff = new CFSecSecGroupFormBuff[ buffList.size() ];
			Iterator<CFSecSecGroupFormBuff> iter = buffList.iterator();
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

	public CFSecSecGroupFormBuff[] readBuffByFormIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecFormId )
	{
		final String S_ProcName = "readBuffByFormIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secgrpfrm_by_formidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByFormIdx == null ) {
				stmtReadBuffByFormIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByFormIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByFormIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByFormIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByFormIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByFormIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByFormIdx.setLong( argIdx++, ClusterId );
			stmtReadBuffByFormIdx.setInt( argIdx++, SecFormId );
			resultSet = stmtReadBuffByFormIdx.executeQuery();
			List<CFSecSecGroupFormBuff> buffList = new LinkedList<CFSecSecGroupFormBuff>();
			while( resultSet.next() ) {
				CFSecSecGroupFormBuff buff = unpackSecGroupFormResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecGroupFormBuff[] retBuff = new CFSecSecGroupFormBuff[ buffList.size() ];
			Iterator<CFSecSecGroupFormBuff> iter = buffList.iterator();
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

	public CFSecSecGroupFormBuff readBuffByUFormIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecGroupId,
		int SecFormId )
	{
		final String S_ProcName = "readBuffByUFormIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_secgrpfrm_by_uformidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByUFormIdx == null ) {
				stmtReadBuffByUFormIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByUFormIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUFormIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByUFormIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByUFormIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUFormIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByUFormIdx.setLong( argIdx++, ClusterId );
			stmtReadBuffByUFormIdx.setInt( argIdx++, SecGroupId );
			stmtReadBuffByUFormIdx.setInt( argIdx++, SecFormId );
			resultSet = stmtReadBuffByUFormIdx.executeQuery();
			if( resultSet.next() ) {
				CFSecSecGroupFormBuff buff = unpackSecGroupFormResultSetToBuff( resultSet );
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

	/**
	 *	Read a page array of the specific SecGroupForm buffer instances identified by the duplicate key ClusterIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argClusterId	The SecGroupForm key attribute of the instance generating the id.
	 *
	 *	@return An array of derived buffer instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFSecSecGroupFormBuff[] pageBuffByClusterIdx( CFSecAuthorization Authorization,
		long ClusterId,
		Long priorClusterId,
		Long priorSecGroupFormId )
	{
		final String S_ProcName = "pageBuffByClusterIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_secgrpfrm_by_clusteridx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtPageBuffByClusterIdx == null ) {
				stmtPageBuffByClusterIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtPageBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtPageBuffByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtPageBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtPageBuffByClusterIdx.setLong( argIdx++, ClusterId );
			if( priorClusterId != null ) {
				stmtPageBuffByClusterIdx.setLong( argIdx++, priorClusterId.longValue() );
			}
			else {
				stmtPageBuffByClusterIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( priorSecGroupFormId != null ) {
				stmtPageBuffByClusterIdx.setLong( argIdx++, priorSecGroupFormId.longValue() );
			}
			else {
				stmtPageBuffByClusterIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtPageBuffByClusterIdx.executeQuery();
			List<CFSecSecGroupFormBuff> buffList = new LinkedList<CFSecSecGroupFormBuff>();
			while( resultSet.next() ) {
				CFSecSecGroupFormBuff buff = unpackSecGroupFormResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecGroupFormBuff[] retBuff = new CFSecSecGroupFormBuff[ buffList.size() ];
			Iterator<CFSecSecGroupFormBuff> iter = buffList.iterator();
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

	/**
	 *	Read a page array of the specific SecGroupForm buffer instances identified by the duplicate key GroupIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argClusterId	The SecGroupForm key attribute of the instance generating the id.
	 *
	 *	@param	argSecGroupId	The SecGroupForm key attribute of the instance generating the id.
	 *
	 *	@return An array of derived buffer instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFSecSecGroupFormBuff[] pageBuffByGroupIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecGroupId,
		Long priorClusterId,
		Long priorSecGroupFormId )
	{
		final String S_ProcName = "pageBuffByGroupIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_secgrpfrm_by_groupidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtPageBuffByGroupIdx == null ) {
				stmtPageBuffByGroupIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtPageBuffByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByGroupIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtPageBuffByGroupIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtPageBuffByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtPageBuffByGroupIdx.setLong( argIdx++, ClusterId );
			stmtPageBuffByGroupIdx.setInt( argIdx++, SecGroupId );
			if( priorClusterId != null ) {
				stmtPageBuffByGroupIdx.setLong( argIdx++, priorClusterId.longValue() );
			}
			else {
				stmtPageBuffByGroupIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( priorSecGroupFormId != null ) {
				stmtPageBuffByGroupIdx.setLong( argIdx++, priorSecGroupFormId.longValue() );
			}
			else {
				stmtPageBuffByGroupIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtPageBuffByGroupIdx.executeQuery();
			List<CFSecSecGroupFormBuff> buffList = new LinkedList<CFSecSecGroupFormBuff>();
			while( resultSet.next() ) {
				CFSecSecGroupFormBuff buff = unpackSecGroupFormResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecGroupFormBuff[] retBuff = new CFSecSecGroupFormBuff[ buffList.size() ];
			Iterator<CFSecSecGroupFormBuff> iter = buffList.iterator();
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

	/**
	 *	Read a page array of the specific SecGroupForm buffer instances identified by the duplicate key AppIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argClusterId	The SecGroupForm key attribute of the instance generating the id.
	 *
	 *	@param	argSecAppId	The SecGroupForm key attribute of the instance generating the id.
	 *
	 *	@return An array of derived buffer instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFSecSecGroupFormBuff[] pageBuffByAppIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecAppId,
		Long priorClusterId,
		Long priorSecGroupFormId )
	{
		final String S_ProcName = "pageBuffByAppIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_secgrpfrm_by_appidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtPageBuffByAppIdx == null ) {
				stmtPageBuffByAppIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtPageBuffByAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByAppIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtPageBuffByAppIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtPageBuffByAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtPageBuffByAppIdx.setLong( argIdx++, ClusterId );
			stmtPageBuffByAppIdx.setInt( argIdx++, SecAppId );
			if( priorClusterId != null ) {
				stmtPageBuffByAppIdx.setLong( argIdx++, priorClusterId.longValue() );
			}
			else {
				stmtPageBuffByAppIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( priorSecGroupFormId != null ) {
				stmtPageBuffByAppIdx.setLong( argIdx++, priorSecGroupFormId.longValue() );
			}
			else {
				stmtPageBuffByAppIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtPageBuffByAppIdx.executeQuery();
			List<CFSecSecGroupFormBuff> buffList = new LinkedList<CFSecSecGroupFormBuff>();
			while( resultSet.next() ) {
				CFSecSecGroupFormBuff buff = unpackSecGroupFormResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecGroupFormBuff[] retBuff = new CFSecSecGroupFormBuff[ buffList.size() ];
			Iterator<CFSecSecGroupFormBuff> iter = buffList.iterator();
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

	/**
	 *	Read a page array of the specific SecGroupForm buffer instances identified by the duplicate key FormIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argClusterId	The SecGroupForm key attribute of the instance generating the id.
	 *
	 *	@param	argSecFormId	The SecGroupForm key attribute of the instance generating the id.
	 *
	 *	@return An array of derived buffer instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFSecSecGroupFormBuff[] pageBuffByFormIdx( CFSecAuthorization Authorization,
		long ClusterId,
		int SecFormId,
		Long priorClusterId,
		Long priorSecGroupFormId )
	{
		final String S_ProcName = "pageBuffByFormIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_secgrpfrm_by_formidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtPageBuffByFormIdx == null ) {
				stmtPageBuffByFormIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtPageBuffByFormIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByFormIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtPageBuffByFormIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtPageBuffByFormIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByFormIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtPageBuffByFormIdx.setLong( argIdx++, ClusterId );
			stmtPageBuffByFormIdx.setInt( argIdx++, SecFormId );
			if( priorClusterId != null ) {
				stmtPageBuffByFormIdx.setLong( argIdx++, priorClusterId.longValue() );
			}
			else {
				stmtPageBuffByFormIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( priorSecGroupFormId != null ) {
				stmtPageBuffByFormIdx.setLong( argIdx++, priorSecGroupFormId.longValue() );
			}
			else {
				stmtPageBuffByFormIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtPageBuffByFormIdx.executeQuery();
			List<CFSecSecGroupFormBuff> buffList = new LinkedList<CFSecSecGroupFormBuff>();
			while( resultSet.next() ) {
				CFSecSecGroupFormBuff buff = unpackSecGroupFormResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecSecGroupFormBuff[] retBuff = new CFSecSecGroupFormBuff[ buffList.size() ];
			Iterator<CFSecSecGroupFormBuff> iter = buffList.iterator();
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

	public void updateSecGroupForm( CFSecAuthorization Authorization,
		CFSecSecGroupFormBuff Buff )
	{
		final String S_ProcName = "updateSecGroupForm";
		ResultSet resultSet = null;
		try {
			long ClusterId = Buff.getRequiredClusterId();
			long SecGroupFormId = Buff.getRequiredSecGroupFormId();
			int SecGroupId = Buff.getRequiredSecGroupId();
			int SecAppId = Buff.getRequiredSecAppId();
			int SecFormId = Buff.getRequiredSecFormId();
			int Revision = Buff.getRequiredRevision();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_update_secgrpfrm( ?, ?, ?, ?, ?, ?" + ", "
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
			stmtUpdateByPKey.setString( argIdx++, "a00d" );
			stmtUpdateByPKey.setLong( argIdx++, ClusterId );
			stmtUpdateByPKey.setLong( argIdx++, SecGroupFormId );
			stmtUpdateByPKey.setInt( argIdx++, SecGroupId );
			stmtUpdateByPKey.setInt( argIdx++, SecAppId );
			stmtUpdateByPKey.setInt( argIdx++, SecFormId );
			stmtUpdateByPKey.setInt( argIdx++, Revision );
			resultSet = stmtUpdateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecSecGroupFormBuff updatedBuff = unpackSecGroupFormResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredSecGroupId( updatedBuff.getRequiredSecGroupId() );
				Buff.setRequiredSecAppId( updatedBuff.getRequiredSecAppId() );
				Buff.setRequiredSecFormId( updatedBuff.getRequiredSecFormId() );
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

	public void deleteSecGroupForm( CFSecAuthorization Authorization,
		CFSecSecGroupFormBuff Buff )
	{
		final String S_ProcName = "deleteSecGroupForm";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long ClusterId = Buff.getRequiredClusterId();
			long SecGroupFormId = Buff.getRequiredSecGroupFormId();

			String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_secgrpfrm( ?, ?, ?, ?, ?" + ", "
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
			stmtDeleteByPKey.setLong( argIdx++, ClusterId );
			stmtDeleteByPKey.setLong( argIdx++, SecGroupFormId );
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

	public void deleteSecGroupFormByIdIdx( CFSecAuthorization Authorization,
		long argClusterId,
		long argSecGroupFormId )
	{
		final String S_ProcName = "deleteSecGroupFormByIdIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_secgrpfrm_by_ididx( ?, ?, ?, ?, ?" + ", "
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
				stmtDeleteByIdIdx.setLong( argIdx++, argClusterId );
				stmtDeleteByIdIdx.setLong( argIdx++, argSecGroupFormId );
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

	public void deleteSecGroupFormByIdIdx( CFSecAuthorization Authorization,
		CFSecSecGroupFormPKey argKey )
	{
		deleteSecGroupFormByIdIdx( Authorization,
			argKey.getRequiredClusterId(),
			argKey.getRequiredSecGroupFormId() );
	}

	public void deleteSecGroupFormByClusterIdx( CFSecAuthorization Authorization,
		long argClusterId )
	{
		final String S_ProcName = "deleteSecGroupFormByClusterIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_secgrpfrm_by_clusteridx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByClusterIdx == null ) {
					stmtDeleteByClusterIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByClusterIdx.setLong( argIdx++, argClusterId );
				resultSet = stmtDeleteByClusterIdx.executeQuery();
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

	public void deleteSecGroupFormByClusterIdx( CFSecAuthorization Authorization,
		CFSecSecGroupFormByClusterIdxKey argKey )
	{
		deleteSecGroupFormByClusterIdx( Authorization,
			argKey.getRequiredClusterId() );
	}

	public void deleteSecGroupFormByGroupIdx( CFSecAuthorization Authorization,
		long argClusterId,
		int argSecGroupId )
	{
		final String S_ProcName = "deleteSecGroupFormByGroupIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_secgrpfrm_by_groupidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByGroupIdx == null ) {
					stmtDeleteByGroupIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByGroupIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByGroupIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByGroupIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByGroupIdx.setLong( argIdx++, argClusterId );
				stmtDeleteByGroupIdx.setInt( argIdx++, argSecGroupId );
				resultSet = stmtDeleteByGroupIdx.executeQuery();
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

	public void deleteSecGroupFormByGroupIdx( CFSecAuthorization Authorization,
		CFSecSecGroupFormByGroupIdxKey argKey )
	{
		deleteSecGroupFormByGroupIdx( Authorization,
			argKey.getRequiredClusterId(),
			argKey.getRequiredSecGroupId() );
	}

	public void deleteSecGroupFormByAppIdx( CFSecAuthorization Authorization,
		long argClusterId,
		int argSecAppId )
	{
		final String S_ProcName = "deleteSecGroupFormByAppIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_secgrpfrm_by_appidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByAppIdx == null ) {
					stmtDeleteByAppIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByAppIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByAppIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByAppIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByAppIdx.setLong( argIdx++, argClusterId );
				stmtDeleteByAppIdx.setInt( argIdx++, argSecAppId );
				resultSet = stmtDeleteByAppIdx.executeQuery();
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

	public void deleteSecGroupFormByAppIdx( CFSecAuthorization Authorization,
		CFSecSecGroupFormByAppIdxKey argKey )
	{
		deleteSecGroupFormByAppIdx( Authorization,
			argKey.getRequiredClusterId(),
			argKey.getRequiredSecAppId() );
	}

	public void deleteSecGroupFormByFormIdx( CFSecAuthorization Authorization,
		long argClusterId,
		int argSecFormId )
	{
		final String S_ProcName = "deleteSecGroupFormByFormIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_secgrpfrm_by_formidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByFormIdx == null ) {
					stmtDeleteByFormIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByFormIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByFormIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByFormIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByFormIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByFormIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByFormIdx.setLong( argIdx++, argClusterId );
				stmtDeleteByFormIdx.setInt( argIdx++, argSecFormId );
				resultSet = stmtDeleteByFormIdx.executeQuery();
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

	public void deleteSecGroupFormByFormIdx( CFSecAuthorization Authorization,
		CFSecSecGroupFormByFormIdxKey argKey )
	{
		deleteSecGroupFormByFormIdx( Authorization,
			argKey.getRequiredClusterId(),
			argKey.getRequiredSecFormId() );
	}

	public void deleteSecGroupFormByUFormIdx( CFSecAuthorization Authorization,
		long argClusterId,
		int argSecGroupId,
		int argSecFormId )
	{
		final String S_ProcName = "deleteSecGroupFormByUFormIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_secgrpfrm_by_uformidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByUFormIdx == null ) {
					stmtDeleteByUFormIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByUFormIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUFormIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByUFormIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByUFormIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUFormIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByUFormIdx.setLong( argIdx++, argClusterId );
				stmtDeleteByUFormIdx.setInt( argIdx++, argSecGroupId );
				stmtDeleteByUFormIdx.setInt( argIdx++, argSecFormId );
				resultSet = stmtDeleteByUFormIdx.executeQuery();
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

	public void deleteSecGroupFormByUFormIdx( CFSecAuthorization Authorization,
		CFSecSecGroupFormByUFormIdxKey argKey )
	{
		deleteSecGroupFormByUFormIdx( Authorization,
			argKey.getRequiredClusterId(),
			argKey.getRequiredSecGroupId(),
			argKey.getRequiredSecFormId() );
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
		S_sqlSelectSecGroupFormDistinctClassCode = null;
		S_sqlSelectSecGroupFormBuff = null;

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
		if( stmtPageBuffAll != null ) {
			try {
				stmtPageBuffAll.close();
			}
			catch( SQLException e ) {
			}
			stmtPageBuffAll = null;
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
		if( stmtPageBuffByClusterIdx != null ) {
			try {
				stmtPageBuffByClusterIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtPageBuffByClusterIdx = null;
		}
		if( stmtPageBuffByGroupIdx != null ) {
			try {
				stmtPageBuffByGroupIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtPageBuffByGroupIdx = null;
		}
		if( stmtPageBuffByAppIdx != null ) {
			try {
				stmtPageBuffByAppIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtPageBuffByAppIdx = null;
		}
		if( stmtPageBuffByFormIdx != null ) {
			try {
				stmtPageBuffByFormIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtPageBuffByFormIdx = null;
		}
		if( stmtDeleteByIdIdx != null ) {
			try {
				stmtDeleteByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByIdIdx = null;
		}
		if( stmtDeleteByClusterIdx != null ) {
			try {
				stmtDeleteByClusterIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByClusterIdx = null;
		}
		if( stmtDeleteByGroupIdx != null ) {
			try {
				stmtDeleteByGroupIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByGroupIdx = null;
		}
		if( stmtDeleteByAppIdx != null ) {
			try {
				stmtDeleteByAppIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByAppIdx = null;
		}
		if( stmtDeleteByFormIdx != null ) {
			try {
				stmtDeleteByFormIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByFormIdx = null;
		}
		if( stmtDeleteByUFormIdx != null ) {
			try {
				stmtDeleteByUFormIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByUFormIdx = null;
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
		if( stmtReadBuffByClusterIdx != null ) {
			try {
				stmtReadBuffByClusterIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByClusterIdx = null;
		}
		if( stmtReadBuffByGroupIdx != null ) {
			try {
				stmtReadBuffByGroupIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByGroupIdx = null;
		}
		if( stmtReadBuffByAppIdx != null ) {
			try {
				stmtReadBuffByAppIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByAppIdx = null;
		}
		if( stmtReadBuffByFormIdx != null ) {
			try {
				stmtReadBuffByFormIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByFormIdx = null;
		}
		if( stmtReadBuffByUFormIdx != null ) {
			try {
				stmtReadBuffByUFormIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByUFormIdx = null;
		}
	}
}
